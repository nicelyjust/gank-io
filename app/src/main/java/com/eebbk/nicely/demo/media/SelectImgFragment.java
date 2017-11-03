package com.eebbk.nicely.demo.media;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.eebbk.nicely.demo.R;
import com.eebbk.nicely.demo.base.fragment.BaseFragment;
import com.eebbk.nicely.demo.media.bean.Image;
import com.eebbk.nicely.demo.media.bean.ImageFolder;
import com.eebbk.nicely.demo.media.config.ImageLoaderListener;
import com.eebbk.nicely.demo.media.config.SelectOptions;
import com.eebbk.nicely.demo.utils.L;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 *  @项目名：  Demo 
 *  @包名：    com.eebbk.nicely.demo.media
 *  @文件名:   SelectImgFragment
 *  @创建者:   lz
 *  @创建时间:  2017/9/26 11:22
 *  @修改时间:  Administrator 2017/9/26 11:22 
 *  @描述：    TODO
 */
public class SelectImgFragment extends BaseFragment implements ImageLoaderListener, OnClickListener {
    private static final String TAG = "SelectImgFragment";
    private static SelectOptions mOptions;
    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.iv_title_select)
    ImageView mIvTitleSelect;
    @BindView(R.id.btn_title_select)
    Button mBtnTitleSelect;
    @BindView(R.id.toolbar)
    RelativeLayout mToolbar;
    @BindView(R.id.rv_img_select)
    RecyclerView mRv;

    private LoaderListener mCursorLoader = new LoaderListener();
    private List<Image> mSelectedImage;
    private ImageAdapter mAdapter;

    public static SelectImgFragment newInstance(SelectOptions options) {
        mOptions = options;

        return new SelectImgFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragemnt_select_img;
    }

    @Override
    protected void initWidget(View root) {
        if (mOptions == null) {
            getActivity().finish();
            return;
        }
        mRv.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mRv.addItemDecoration(new SpaceGridItemDecoration());
        mAdapter = new ImageAdapter(getContext(), this);
        mAdapter.setSingleSelect(mOptions.getSelectCount() <= 1);
        mAdapter.setOnClickListener(this);
        mRv.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mSelectedImage = new ArrayList<>();

        // 加载数据
        getLoaderManager().initLoader(0, null, mCursorLoader);
    }

    @Override
    public void displayImg(ImageView iv, String path) {
        getImgLoader().load(path)
                .asBitmap()
                .centerCrop()
                .error(R.mipmap.ic_split_graph)
                .into(iv);
    }

    @Override
    public void onItemClickListener(int pos) {
        List<Image> datas = mAdapter.getDatas();
        if (datas == null || datas.isEmpty()) {
            return;
        }
        Image image = datas.get(pos);
        int selectCount = mOptions.getSelectCount();
        if (selectCount > 1) {
            if (mSelectedImage == null) {
                return;
            }
            if (image.isSelect()){
                mSelectedImage.remove(image);
                image.setSelect(false);
                mAdapter.notifyItemChanged(pos);
            } else if (selectCount <= mSelectedImage.size()) {
                Toast.makeText(getActivity(), "最多只能选择 " + selectCount + " 张照片", Toast.LENGTH_SHORT).show();
            } else {
                mSelectedImage.add(image);
                image.setSelect(true);
                mAdapter.notifyItemChanged(pos);
            }
            // 多选

        } else {
            // todo 单选 要进入剪裁界面

        }
//        mOptions.getCallback().doSelected(new String[]{image.getPath()});
    }

    private class LoaderListener implements LoaderManager.LoaderCallbacks<Cursor> {
        private final String[] IMAGE_PROJECTION = {
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_ADDED,
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.MINI_THUMB_MAGIC,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            if (id == 0) {
                //数据库光标加载器
                return new CursorLoader(getContext(),
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION,
                        null, null, IMAGE_PROJECTION[2] + " DESC");
            }
            return null;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, final Cursor data) {
            if (data == null) {
                return;
            }

            final ArrayList<Image> images = new ArrayList<>();
            final List<ImageFolder> imageFolders = new ArrayList<>();

            final ImageFolder defaultFolder = new ImageFolder();
            defaultFolder.setName("全部照片");
            defaultFolder.setPath("");
            imageFolders.add(defaultFolder);

            int count = data.getCount();
            if (count <= 0) {
                return;
            }
            data.moveToFirst();
            do {
                String path = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[0]));
                String name = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[1]));
                long dateTime = data.getLong(data.getColumnIndexOrThrow(IMAGE_PROJECTION[2]));
                int id = data.getInt(data.getColumnIndexOrThrow(IMAGE_PROJECTION[3]));
                String thumbPath = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[4]));
                String bucket = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[5]));

                Image image = new Image();
                image.setPath(path);
                image.setName(name);
                image.setDate(dateTime);
                image.setId(id);
                image.setThumbPath(thumbPath);
                image.setFolderName(bucket);

                images.add(image);


                File imageFile = new File(path);
                File folderFile = imageFile.getParentFile();
                ImageFolder folder = new ImageFolder();
                folder.setName(folderFile.getName());
                folder.setPath(folderFile.getAbsolutePath());
                if (!imageFolders.contains(folder)) {
                    folder.getImages().add(image);
                    folder.setAlbumPath(image.getPath());//默认相册封面
                    imageFolders.add(folder);
                } else {
                    // 更新
                    ImageFolder f = imageFolders.get(imageFolders.indexOf(folder));
                    f.getImages().add(image);
                }


            } while (data.moveToNext());
            L.d(TAG, images);
            defaultFolder.getImages().addAll(images);
            if (mOptions.isHasCam()) {
                defaultFolder.setAlbumPath(images.size() > 1 ? images.get(1).getPath() : null);
            } else {
                defaultFolder.setAlbumPath(images.size() > 0 ? images.get(0).getPath() : null);
            }
            // 更新adapter
            mAdapter.setData(images);
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
